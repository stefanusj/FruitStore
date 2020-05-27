package com.stefanusj.fruitstore.data.network

import com.google.gson.Gson
import com.stefanusj.fruitstore.model.Fruit
import com.stefanusj.fruitstore.model.Image
import com.stefanusj.fruitstore.model.response.FruitResponse
import okhttp3.*

/**
 * A list of fake images to return.
 */
private fun generateImages(photoId: String, small: Int = 512, normal: Int = 1024) = Image(
    small = "https://images.unsplash.com/$photoId?w=$small&h=$small",
    high = "https://images.unsplash.com/$photoId?w=$normal&h=$normal"
)

/**
 * A list of fake results to return.
 */
private val FAKE_RESULTS_FRUITS = listOf(
    Fruit(
        id = 1,
        image = generateImages("photo-1557800636-894a64c1696f"),
        name = "Jeruk",
        unit = "kg",
        price = 12_500
    ),
    Fruit(
        id = 2,
        image = generateImages("photo-1589533610925-1cffc309ebaa"),
        name = "Stroberi",
        unit = "pak",
        price = 13_500
    ),
    Fruit(
        id = 3,
        image = generateImages("photo-1587825045005-c9cc5fa27203"),
        name = "Alpukat",
        unit = "kg",
        price = 20_000
    ),
    Fruit(
        id = 4,
        image = generateImages("photo-1571771894821-ce9b6c11b08e"),
        name = "Pisang",
        unit = "sisir",
        price = 30_000
    ),
    Fruit(
        id = 5,
        image = generateImages("photo-1550258987-190a2d41a8ba"),
        name = "Nanas",
        unit = "buah",
        price = 23_500
    ),
    Fruit(
        id = 6,
        image = generateImages("photo-1572635148818-ef6fd45eb394"),
        name = "Lemon",
        unit = "kg",
        price = 25_400
    ),
    Fruit(
        id = 7,
        image = generateImages("photo-1517282009859-f000ec3b26fe"),
        name = "Pepaya",
        unit = "kg",
        price = 28_200
    ),
    Fruit(
        id = 8,
        image = generateImages("photo-1554795808-3231c32711cf"),
        name = "Bluberi",
        unit = "pak",
        price = 60_000
    ),
    Fruit(
        id = 9,
        image = generateImages("photo-1571575173700-afb9492e6a50"),
        name = "Melon",
        unit = "buah",
        price = 38_000
    ),
    Fruit(
        id = 10,
        image = generateImages("photo-1579247289081-dc2a9832539c"),
        name = "Apel",
        unit = "kg",
        price = 32_000
    )
)

/**
 * This class will return fake [Response] objects to Retrofit, without actually using the network.
 */
class SkipNetworkInterceptor : Interceptor {

    private val gson = Gson()

    /**
     * Stop the request from actually going out to the network.
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        pretendToBlockForNetworkRequest()
        return makeOkResult(chain.request())
    }

    /**
     * Pretend to "block" interacting with the network.
     *
     * Really: sleep for 1000ms.
     */
    private fun pretendToBlockForNetworkRequest() = Thread.sleep(1_000)

    /**
     * Generate a success response.
     *
     * ```
     * HTTP/1.1 200 OK
     * Content-type: application/json
     *
     * "FruitResponse(...)"
     * ```
     */
    private fun makeOkResult(request: Request): Response {
        return Response.Builder()
            .code(200)
            .request(request)
            .protocol(Protocol.HTTP_1_1)
            .message("OK")
            .body(
                ResponseBody.create(
                    MediaType.get("application/json"),
                    gson.toJson(
                        FruitResponse(
                            status = true,
                            data = FAKE_RESULTS_FRUITS
                        )
                    )
                )
            )
            .build()
    }
}