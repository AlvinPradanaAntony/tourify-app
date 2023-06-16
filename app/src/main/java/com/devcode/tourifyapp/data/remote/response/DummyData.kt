package com.devcode.tourifyapp.data.remote.response

import com.devcode.tourifyapp.R

object DummyData {
    val dummyBanner = listOf(
        TravelBanner(
            R.drawable.banner1
        ),
        TravelBanner(
            R.drawable.banner2
        ),
        TravelBanner(
            R.drawable.banner3
        ),
        TravelBanner(
            R.drawable.banner4
        ),
        TravelBanner(
            R.drawable.banner5
        )
    )
    val dummyDataTravel = listOf(
        TravelDataDummyResponse(
            R.drawable.klenteng,
            "Klenteng Sumber Agung",
            "Klenteng Sumber Agung merupakan salah satu tempat wisata religi yang ada di Kota Batu. Klenteng ini berada di Jalan Sultan Agung, Kelurahan Songgokerto, Kecamatan Batu. Klenteng ini merupakan tempat ibadah umat Budha yang ada di Kota Batu. Klenteng ini juga merupakan salah satu tempat wisata religi yang ada di Kota Batu. Klenteng ini berada di Jalan Sultan Agung, Kelurahan Songgokerto, Kecamatan Batu. Klenteng ini merupakan tempat ibadah umat Budha yang ada di Kota Batu.",
        ),
        TravelDataDummyResponse(
            R.drawable.img_sample,
            "Some Place 1",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla euismod, nisl vitae aliquam ultricies, nunc nisl ultricies nunc, vitae a consequat nisl nisl vitae ni sl. Nulla euismod, nisl vitae aliquam ultricies, nunc nisl ultricies nunc, vitae a consequat nisl nisl vitae ni sl.",
        ),
        TravelDataDummyResponse(
            R.drawable.img_sample2,
            "Pantai Kenjeran",
            "Pantai Kenjeran merupakan salah satu tempat wisata yang ada di Kota Surabaya. Pantai ini berada di Jalan Pantai Lama, Kelurahan Kenjeran, Kecamatan Bulak, Kota Surabaya. Pantai ini merupakan salah satu tempat wisata yang ada di Kota Surabaya. Pantai ini berada di Jalan Pantai Lama, Kelurahan Kenjeran, Kecamatan Bulak, Kota Surabaya.",
        ),
        TravelDataDummyResponse(
            R.drawable.img_sample3,
            "Monumen Kapal Selam",
            "Monumen Kapal Selam merupakan salah satu tempat wisata yang ada di Kota Surabaya. Monumen ini berada di Jalan Pemuda, Kelurahan Pabean Cantian, Kecamatan Bubutan, Kota Surabaya. Monumen ini merupakan salah satu tempat wisata yang ada di Kota Surabaya. Monumen ini berada di Jalan Pemuda, Kelurahan Pabean Cantian, Kecamatan Bubutan, Kota Surabaya.",
        ),
        TravelDataDummyResponse(
            R.drawable.img_sample4,
            "Monumen Tugu Pahlawan",
            "Monumen Tugu Pahlawan merupakan salah satu tempat wisata yang ada di Kota Surabaya. Monumen ini berada di Jalan Pahlawan, Kelurahan Embong Kaliasin, Kecamatan Genteng, Kota Surabaya. Monumen ini merupakan salah satu tempat wisata yang ada di Kota Surabaya. Monumen ini berada di Jalan Pahlawan, Kelurahan Embong Kaliasin, Kecamatan Genteng, Kota Surabaya.",
        )
    )
    val dummyDataReviews = listOf(
        ReviewsResponse(
            1,
            393,
            5.0,
            "I had an incredible experience at the XYZ tourist attraction! The breathtaking views and immersive atmosphere made it a truly memorable visit.",
            "2021-08-01T00:00:00.000Z",
            "John Doe",
            "https://i.pravatar.cc/150?img=1"
        ),
        ReviewsResponse(
            6,
            393,
            2.0,
            "Visiting XYZ was a highlight of my trip! The well-preserved landmarks and knowledgeable guides added so much depth to the experience.",
            "2021-08-01T00:00:00.000Z",
            "Username 2",
            "https://i.pravatar.cc/150?img=2"
        ),
        ReviewsResponse(
            8,
            393,
            4.5,
            "XYZ exceeded my expectations in every way. The friendly staff, stunning architecture, and fascinating history make it a must-visit destination.",
            "2021-08-01T00:00:00.000Z",
            "Username 3",
            "https://i.pravatar.cc/150?img=3"
        ),

    )
}

