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
            "Klenteng Sumber Agung Panjeran adalah sebuah klenteng yang terletak di Jalan Panjeran, Surabaya. Klenteng ini merupakan salah satu tempat ibadah dan objek wisata religius yang menarik bagi pengunjung. Terletak di pusat kota, klenteng ini mudah diakses dengan kendaraan pribadi atau transportasi umum.\n" +
                    "\n" +
                    "Klenteng Sumber Agung Panjeran memiliki sejarah yang kaya. Dibangun pada abad ke-18, klenteng ini telah menjadi pusat spiritual bagi umat Konghucu di Surabaya selama berabad-abad. Arsitektur klenteng ini menggabungkan elemen-elemen tradisional Tiongkok dengan sentuhan lokal, menciptakan suasana yang khas dan memikat.",
        ),
        TravelDataDummyResponse(
            R.drawable.img_sample1,
            "Taman Bungkul",
            "Taman Bungkul adalah destinasi wisata populer di Surabaya, Indonesia. Terletak di pusat kota, taman ini mudah diakses dari berbagai tempat. Dengan suasana yang hijau dan sejuk, Taman Bungkul menawarkan lingkungan yang nyaman dan menarik. Pengunjung dapat menikmati keindahan pepohonan rindang, taman bunga yang indah, dan area rumput yang luas. Fasilitas rekreasi seperti jogging track, area bermain anak-anak, dan area piknik juga tersedia untuk dinikmati oleh pengunjung. Di food court, pengunjung dapat menikmati berbagai makanan dan minuman lezat. Taman Bungkul juga sering menjadi tuan rumah acara dan pertunjukan yang menarik, serta menyediakan fasilitas olahraga seperti lapangan basket dan lapangan sepak bola.\n Keamanan dan kebersihan dijaga dengan baik, dan terdapat petugas keamanan serta toilet umum yang bersih. Taman Bungkul adalah tempat yang ideal untuk bersantai, berolahraga, dan menikmati waktu bersama keluarga dan teman-teman.",
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

