package com.smart.smartcontactmanager.dao;

import com.smart.smartcontactmanager.Entities.ContactCard;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactCardRepo extends JpaRepository<ContactCard,Integer>{
    @Query(value = "select c from ContactCard c where c.user.u_id= :userid")
    List<ContactCard> findContactCardsByUser_U_id(@Param("userid") int userId);

    @Query(value = "select c from ContactCard c where c.cId=:id")
    ContactCard findContactCardsByCId(@Param("id") int id);

//    @Modifying
//    @Query(value = "delete from contact_card where c_id=:id",nativeQuery = true)
//    void deleteContactCardByCId(@Param("id") int id);

    @Transactional
    void deleteByCId(int id);
}
