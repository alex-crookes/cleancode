package com.alexcrookes.provider.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.alexcrookes.provider.room.entity.ArtistRow
import com.alexcrookes.provider.room.entity.CollectionReleaseRow
import com.alexcrookes.provider.room.entity.CollectionReleaseRowWithMeta
import com.alexcrookes.provider.room.entity.CollectionTable
import com.alexcrookes.provider.room.entity.FormatRow
import com.alexcrookes.provider.room.entity.GenresRow
import com.alexcrookes.provider.room.entity.LabelRow
import com.alexcrookes.provider.room.entity.StylesRow

@Dao
interface CollectionDao {

	@Transaction
	@Query("SELECT * FROM $CollectionTable")
	suspend fun getCollection(): List<CollectionReleaseRowWithMeta>

	@Transaction
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insert(collectionRelease: CollectionReleaseRowWithMeta) {
		insertSingleRelease(collectionRelease.row)
		insertLabel(collectionRelease.labels)
		insertStyle(collectionRelease.styles)
		insertFormats(collectionRelease.formats)
		insertGenre(collectionRelease.genres)
		insertArtist(collectionRelease.artists)
	}


	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertAll(vararg collectionReleases: CollectionReleaseRowWithMeta) {
		collectionReleases.forEach {
			insert(it)
		}
	}

	@Query("DELETE FROM $CollectionTable")
	suspend fun clear()

	/**
	 * Determine if a release with ID is present in the collection
	 *
	 * @param id (Long) id of the release
	 *
	 * @return number of rows in the collection
	 */
	@Query("SELECT count(*) from $CollectionTable where id = :id")
	suspend fun collectionSize(id: Long): Int

	@Query("SELECT count(*) from $CollectionTable")
	suspend fun collectionSize(): Int

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertSingleRelease(collectionRelease: CollectionReleaseRow)

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertLabel(labels: List<LabelRow>)

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertGenre(labels: List<GenresRow>)

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertStyle(labels: List<StylesRow>)

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertArtist(labels: List<ArtistRow>)

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertFormats(labels: List<FormatRow>)


}
