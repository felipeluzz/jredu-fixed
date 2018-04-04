package br.com.developer.redu.api;

import java.util.List;

/**
 * @author Felipe Luzzardi
 *
 * @param <X> - Tipo do status
 */

public interface ProgressMethods<X> {

    /**
     * Retorna um progresso com o ID especificado.
     * @param progressid - ID do status
     * @return Um progresso
     */

    public X getProgress(String progressid);

    /**
     * Retorna uma lista com os progressos da lecture informada.
     * @param lectureid - ID da lecture
     * @return Lista de progressos
     */

    public List<X> getProgressByLecture(String lectureid);

    /**
     * Retorna uma lista com os progressos do subject informado.
     * @param subjectid - ID do subject
     * @return Lista de progressos
     */

    public List<X> getProgressBySubject(String subjectid);

    /**
     * Retorna uma lista com os progressos do usuário informado.
     * @param userid - ID do usuário
     * @return Lista de progressos
     */

    public List<X> getProgressByUser(String userid);

}
