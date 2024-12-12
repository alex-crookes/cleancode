package com.alexcrookes.provider.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alexcrookes.provider.room.entity.ArtistRow
import com.alexcrookes.provider.room.entity.CollectionArtistsCrossRefRow
import com.alexcrookes.provider.room.entity.CollectionFormatsCrossRefRow
import com.alexcrookes.provider.room.entity.CollectionGenresCrossRefRow
import com.alexcrookes.provider.room.entity.CollectionLabelsCrossRefRow
import com.alexcrookes.provider.room.entity.CollectionReleaseRow
import com.alexcrookes.provider.room.entity.CollectionStylesCrossRefRow
import com.alexcrookes.provider.room.entity.FormatRow
import com.alexcrookes.provider.room.entity.GenresRow
import com.alexcrookes.provider.room.entity.LabelRow
import com.alexcrookes.provider.room.entity.ListReleaseRow
import com.alexcrookes.provider.room.entity.StylesRow

//@Database(
//	version=1,
//	entities = [
//		CollectionReleaseRow::class,
//		ListReleaseRow::class
//    ],
//	exportSchema = false
//)

@Database(
	version=1,
	entities = [
		CollectionReleaseRow::class,
		FormatRow::class,
		CollectionFormatsCrossRefRow::class,
		GenresRow::class,
		CollectionGenresCrossRefRow::class,
		LabelRow::class,
		CollectionLabelsCrossRefRow::class,
		StylesRow::class,
		CollectionStylesCrossRefRow::class,
		ArtistRow::class,
		CollectionArtistsCrossRefRow::class,
		ListReleaseRow::class
	],
//	autoMigrations = [
//		//AutoMigration (from = 1, to = 2)
//	],
	//exportSchema = false
)
abstract class CollectionDatabase: RoomDatabase() {
	abstract fun collectionDao(): CollectionDao
}
