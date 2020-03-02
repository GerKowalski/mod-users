package org.folio.modusers.repository;

import java.util.UUID;

import org.folio.modusers.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>
{

}
