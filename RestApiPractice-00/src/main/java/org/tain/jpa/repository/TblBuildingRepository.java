package org.tain.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.tain.jpa.domain.TblBuilding;

@RepositoryRestResource
public interface TblBuildingRepository extends JpaRepository<TblBuilding, Long>{

}
