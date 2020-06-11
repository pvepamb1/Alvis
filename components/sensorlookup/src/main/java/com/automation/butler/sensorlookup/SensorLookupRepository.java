package com.automation.butler.sensorlookup;

import com.automation.butler.enums.SensorType;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Specifies methods used to obtain and modify sensor-lookup related information
 * which is stored in the database.
 */

public interface SensorLookupRepository extends CrudRepository<SensorLookup, SensorLookupID> {
	
	/**
     * Finds sensor by using the type as a search criteria.
	 *
     * @param type A SensorType describing the type of sensor
     * @return  A list of sensors whose type is an exact match with the given type.
     *          If no sensors are found, this method returns null.
     */
	Iterable<SensorLookup> findByType(SensorType type);

	@Query(value = "SELECT * FROM sensor_lookup sensor WHERE sensor.address_mac= ?1 and sensor.id= ?2",
			nativeQuery = true)
	Optional<SensorLookup> findByMacAndId(String mac, String id);

    @Query(value = "SELECT * FROM sensor_lookup sensor WHERE sensor.address_mac= ?1",
            nativeQuery = true)
    List<Optional<SensorLookup>> findByMac(String mac);
	
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO sensor_lookup (alias, type, address_mac, id) values (NULL, NULL, ?1, ?2)",
			nativeQuery = true)
	void saveUnmapped(String mac, String id);
	
	@Query(value = "SELECT ip FROM device_address da, sensor_lookup sl WHERE sl.alias =?1 and sl.address_mac = da.mac",
			nativeQuery = true)
	Optional<String> findIpByAlias(String alias);
}
