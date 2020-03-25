package br.com.kbmg.wsconsultamedica.service;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface GenericService<T> {

	/**
	 * Criar um novo registro da entidade.
	 * 
	 * @param entity - objeto para salvar.
	 * @return O registro criado.
	 */
	public T create(T entity);

	/**
	 * Atualiza uma entidade. Realiza uma busca para verificar se o id
	 * existe, transfere as informações presentes no dto para a entidade, salva as
	 * alterações e por fim realiza um parse para o tipo especificado por
	 * typeReturn.
	 * 
	 * @param id         - identificador da entidade.
	 * @param dto        - Objeto com os novos dados.
	 * @param typeReturn - tipo da classe de retorno.
	 * @return O registro atualizado.
	 */
	Object update(String id, Object dto, Class<?> typeReturn);

	/**
	 * Busca a entidade por ID.
	 * 
	 * @param id - identificador único.
	 * 
	 * @return O registro encontrado.
	 * @throws InvalidParameterException - Se id não for um número
	 * @throws EntityNotFoundException-  Se não existir.
	 */
	public T findById(String id);

	/**
	 * Busca a entidade por ID e retorna um DTO especificado por typeConvert.
	 * 
	 * @param id          - identificador único.
	 * @param typeConvert - tipo do DTO para conversão da entidade.
	 * 
	 * @return O registro encontrado.
	 * @throws InvalidParameterException - Se id não for um número
	 * @throws EntityNotFoundException-  Se não existir.
	 */
	Object findById(String id, Class<?> typeConvert);

	/**
	 * Busca uma lista de todos os registros.
	 * 
	 * @return A lista de entidades.
	 * @throws EntityNotFoundException Se a tabela estiver vazia.
	 */
	public List<T> findAll();

	/**
	 * Busca todas as entidades e retorna o DTO equivalente.
	 * 
	 * @param typeConvert - Class do DTO para conversão
	 * 
	 * @return A lista de DTO's.
	 * @throws EntityNotFoundException Se a tabela estiver vazia.
	 */
	List<?> findAllDto(Class<?> typeConvert);

	/**
	 * Cria uma lista paginada dos registros.
	 * 
	 * @param page         - número da página desejada.
	 * @param size         - quantidade de elementos por página.
	 * @param direction    - ASC (crescente) || DESC (decrescente).
	 * @param sortProperty - atributo base do objeto para ser ordenado.
	 * 
	 * @return A paginação baseada nos parâmetros passados.
	 */
	public Page<T> findAllPaginated(int page, int size, Sort.Direction direction, String sortProperty);

	/**
	 * Apaga um registro pela entidade.
	 * 
	 * @param entity - Objeto que será deletado.
	 */
	public void delete(T entity);

	/**
	 * Apaga um registro por ID.
	 * 
	 * @param id - identificador do objeto que será apagado.
	 */
	public void deleteById(String id);

	/**
	 * Apaga uma lista de entidades.
	 * 
	 * @param entitys - Coleção de entidades que serão deletadas.
	 */
	public void deleteAll(Set<T> entitys);

	/**
	 * Apaga uma lista de entidades somente informando os IDs.
	 * 
	 * @param id - lista de identificadores que serão deletados
	 * 
	 * @throws EntityNotFoundException Se na lista houver algum id que não existe.
	 */
	public void deleteAllById(Set<Long> id);

	/**
	 * 
	 * @param entity 
	 * @return a entidade salva no banco 
	 * 
	 *  @throws IllegalAccessException - Se ocorrer algum erro.
	 */
	T saveEntity(T entity);

}
