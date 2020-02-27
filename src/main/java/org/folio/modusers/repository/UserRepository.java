package org.folio.modusers.repository;

import java.util.Optional;

import javax.ws.rs.PathParam;

import org.folio.modusers.entity.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String>
{

}
