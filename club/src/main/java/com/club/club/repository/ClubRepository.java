package com.club.club.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.club.club.model.Club;

@Repository
public interface ClubRepository extends JpaRepository<Club, Integer> {
    /************************************************************************************************
     * 1 2 métodos de búsqueda usando nomenclatura Spring Data (findBy...) usando
     * varias condiciones
     * 
     * @param nombre
     * @param ciudad
     * @return
     ************************************************************************************************/
    // Estos son metodos de busqueda los cuales , en mi caso van a servir para
    // encontrar clubs por su nombre que esten en una ciudad concreta.
    List<Club> findByNombreContainingAndCiudad(String nombre, String ciudad);

    // este metodo busca clubs fundados antes de una fecha concreta en una ciudad
    // concreta tambien.
    List<Club> findByFundacionBeforeAndCiudad(String fundacion, String ciudad);

    /*************************************************************************************************
     * 2 método con ordenación
     ************************************************************************************************/
    // en concreto este metodo ordenará los clubs por su nombre de forma ascendente.
    List<Club> findAllByOrderByNombreAsc();

    /*******************************************************************************************
     * 3 método de conteo y agregación
     **********************************************************************************************/
    // el de conteo cuenta cuantos clubs hay en una ciudad concreta.
    long countByCiudad(String ciudad);

    // el de agregacion encuentra el club mas antiguo.
    Club findTopByOrderByFundacionAsc();

    /*
     * *****************************************************************************
     * ****************
     * 4 consulta personalizada con @Query (JPQL)
     **********************************************************************************************/
    // este metodo busca clubs en una ciudad concreta que tengan mas de un numero
    // minimo de socios.
    @Query("SELECT c FROM Club c WHERE c.ciudad = :ciudad AND c.numSocios > :minSocios")
    List<Club> buscarPorCiudadYSocios(@Param("ciudad") String ciudad, @Param("minSocios") int minSocios);

    Optional<Club> findById(Long id);

    void deleteById(Long id);
}
