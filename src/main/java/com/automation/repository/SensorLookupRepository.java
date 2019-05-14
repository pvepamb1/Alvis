package com.automation.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.automation.embeddable.SensorLookupID;
import com.automation.enums.SensorType;
import com.automation.table.SensorLookup;

/**
 * Specifies methods used to obtain and modify sensor-lookup related information
 * which is stored in the database.
 */

public interface SensorLookupRepository extends CrudRepository<SensorLookup, SensorLookupID> {
	
	/**
     * Finds sensor by using the type as a search criteria.
     * @param type
     * @return  A list of sensors whose type is an exact match with the given type.
     *          If no sensors are found, this method returns null.
     */
	Iterable<SensorLookup> findByType(SensorType type);
	
	/**
     * Finds sensor by using the alias as a search criteria.
     * @param alias
     * @return  A list of sensors whose alias is an exact match with the given alias.
     *          If no sensors are found, this method returns null.
     */
	Optional<SensorLookup> findByAlias(String alias);
}
