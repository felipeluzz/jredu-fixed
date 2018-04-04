package br.com.developer.redu.api;

import java.util.List;

/**
 * @author Felipe Luzzardi
 *
 * @param <Z> - Tipo do status
 */


public interface LectureMethods<Z> {

    /**
     * Retorna uma aula com o ID especificado.
     * @param lectureid - ID da aula
     * @return Uma aula
     */

    public Z getLecture(String lectureid);

    /**
     * Retorna uma lista com as aulas do subject informado.
     * @param subjectid - ID do subject
     * @return Lista de aulas
     */

    public List<Z> getLectureBySubject(String subjectid);
}
