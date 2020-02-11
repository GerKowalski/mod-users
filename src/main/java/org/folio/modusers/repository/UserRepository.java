package org.folio.modusers.repository;

import java.util.Optional;

import org.folio.modusers.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String>
{

}
