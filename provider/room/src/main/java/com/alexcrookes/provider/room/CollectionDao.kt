package com.alexcrookes.provider.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alexcrookes.provider.room.entity.CollectionReleaseRow
import com.alexcrookes.provider.room.entity.CollectionTable

@Dao
interface CollectionDao {
	@Query("SELECT * FROM $CollectionTable")
	suspend fun getCollection(): List<CollectionReleaseRow>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insert(collectionRelease: CollectionReleaseRow)

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertAll(vararg collectionReleases: CollectionReleaseRow)

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
	suspend fun collectionContains(id: Long): Int
}
