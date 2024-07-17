package idp.ext

import com.example.sandbox.R

object Mock {
    val items =
        listOf(
            Item(
                iconRes = R.drawable.ic_home_24,
                text = "Home",
            ),
            Item(
                iconRes = R.drawable.ic_briefcase_24,
                text = "Order",
            ),
            Item(
                iconRes = R.drawable.ic_heart_24,
                text = "Tersimpan",
            ),
            Item(
                iconRes = R.drawable.ic_comment_24,
                text = "Pesan",
                priority = true,
            ),
            Item(
                iconRes = R.drawable.ic_person_24,
                text = "Profil",
            ),
        )

    val item = items.first()

    val musicList =
        listOf(
            Music(
                name = "Chill Lofi Synth Hip Hop Loop",
                artist = "Mafalij",
                imageRes = R.drawable.img_unkown,
                musicRes = R.raw.loopazon,
            ),
            Music(
                name = "Frank Ocean Influenced",
                artist = "DamoreMusicSamples",
                imageRes = R.drawable.img_damore_music_samples,
                musicRes = R.raw.loopazon2,
            ),
            Music(
                name = "Tomppabeats X Bonobo Serenity Bells",
                artist = "toloxo",
                imageRes = R.drawable.img_toloxo,
                musicRes = R.raw.loopazon3,
            ),
        )
}