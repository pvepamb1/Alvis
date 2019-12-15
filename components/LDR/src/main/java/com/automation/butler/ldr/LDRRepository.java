package com.automation.butler.ldr;

import org.springframework.data.repository.CrudRepository;

/**
 * Specifies methods used to obtain and modify LDR related information
 * which is stored in the database.
 */

public interface LDRRepository extends CrudRepository<LDRDTO, String> {

}
