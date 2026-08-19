package com.workintech.sqldmljoins.repository;

import com.workintech.sqldmljoins.entity.Kitap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KitapRepository extends JpaRepository<Kitap, Long> {

    //Dram ve Hikaye türündeki kitapları listeleyin. JOIN kullanmadan yapın.
    String QUESTION_1 = "SELECT * FROM kitap WHERE turno IN (SELECT turno FROM tur WHERE ad='Dram' OR ad='Hikaye')";
    @Query(value = QUESTION_1, nativeQuery = true)
    List<Kitap> findBooks();

    //Tüm kitapların ortalama puanını bulunuz.
    String QUESTION_10 = "SELECT avg(puan) as kitap_puan_ortalama FROM kitap";
    @Query(value = QUESTION_10, nativeQuery = true)
    Double findAvgPointOfBooks();


}
