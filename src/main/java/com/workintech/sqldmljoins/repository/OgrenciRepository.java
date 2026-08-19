package com.workintech.sqldmljoins.repository;

import com.workintech.sqldmljoins.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OgrenciRepository extends JpaRepository<Ogrenci, Long> {


    //Kitap alan öğrencilerin öğrenci bilgilerini listeleyin..
    String QUESTION_2 = "SELECT * FROM ogrenci WHERE ogrno IN (SELECT ogrno FROM islem)";
    @Query(value = QUESTION_2, nativeQuery = true)
    List<Ogrenci> findStudentsWithBook();


    //Kitap almayan öğrencileri listeleyin.
    String QUESTION_3 = "SELECT * FROM ogrenci WHERE ogrno NOT IN (SELECT ogrno FROM islem)";
    @Query(value = QUESTION_3, nativeQuery = true)
    List<Ogrenci> findStudentsWithNoBook();

    //10A veya 10B sınıfındaki öğrencileri sınıf ve okuduğu kitap sayısını getirin.
    String QUESTION_4 = "SELECT o.sinif, COUNT(i.kitapno) AS kitap_sayisi FROM ogrenci o JOIN islem i ON o.ogrno = i.ogrno WHERE o.sinif IN ('10A', '10B') GROUP BY o.sinif;";
    @Query(value = QUESTION_4, nativeQuery = true)
    List<KitapCount> findClassesWithBookCount();

    //Öğrenci tablosundaki öğrenci sayısını gösterin
    String QUESTION_5 = "SELECT COUNT(ogrno) AS ogrenci_sayisi FROM ogrenci";
    @Query(value = QUESTION_5, nativeQuery = true)
    Integer findStudentCount();

    //Öğrenci tablosunda kaç farklı isimde öğrenci olduğunu listeleyiniz.
    String QUESTION_6 = "SELECT COUNT(DISTINCT ad) AS farkli_isim_sayisi FROM ogrenci";
    @Query(value = QUESTION_6, nativeQuery = true)
    Integer findUniqueStudentNameCount();

    //--İsme göre öğrenci sayılarının adedini bulunuz.
    //--Ali: 2, Mehmet: 3
    String QUESTION_7 = "select ad,count(ad) from ogrenci group by ad";
    @Query(value = QUESTION_7, nativeQuery = true)
    List<StudentNameCount> findStudentNameCount();

    //Her sınıftaki öğrenci sayısını bulunuz.
    String QUESTION_8 = "select sinif,count(ogrno) from ogrenci group by sinif";
    @Query(value = QUESTION_8, nativeQuery = true)
    List<StudentClassCount> findStudentClassCount();


    //Her öğrencinin ad soyad karşılığında okuduğu kitap sayısını getiriniz.
    String QUESTION_9 = "SELECT o.ad, o.soyad, COUNT(i.kitapno) AS kitap_sayisi FROM ogrenci o JOIN islem i ON o.ogrno = i.ogrno GROUP BY o.ogrno, o.ad, o.soyad;";
    @Query(value = QUESTION_9, nativeQuery = true)
    List<StudentNameSurnameCount> findStudentNameSurnameCount();
}
