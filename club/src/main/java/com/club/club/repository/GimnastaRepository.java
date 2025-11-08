package com.club.club.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.club.club.model.Gimnasta;

@Repository
public interface GimnastaRepository extends JpaRepository<Gimnasta, Integer> {

    /**************************************************************************
     * 1. 2 metodos para borrar registros que cumplan x condiciones
     *************************************************************************/
    //este metodo borra gimnastas que sean menores de una edad concreta.
     void deleteByEdadLessThan(int edad);
    //este metodo borra gimnastas que pertenezcan a un club con un nombre concreto.
     void deleteByClubNombre(String nombreClub);
    
    /********************************************************************************
     * 2. metodo de busqueda con filtro
     ******************************************************************************/
    //este metodo busca gimnastas que tengan una especialidad concreta y sean mayores de una edad concreta.
     List<Gimnasta> findByEspecialidadAndEdadGreaterThan(String especialidad, int edad);
    
    /********************************************************************************
     * 3. consulta personalizada
    ******************************************************************************** */
    //esta consulta busca gimnastas que pertenezcan a clubs de una ciudad en concreto
    @Query("SELECT g FROM Gimnasta g WHERE g.club.ciudad = :ciudad")
    List<Gimnasta> buscarPorCiudadClub(@Param("ciudad") String ciudad);
}
