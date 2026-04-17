package br.edu.ifgoiano.clickdriveapi.repository;

import br.edu.ifgoiano.clickdriveapi.model.QuestaoModel;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface QuestaoRepository extends JpaRepository<QuestaoModel, Long> {

    List<QuestaoModel> findByCategoria(String categoria);

    @Override
    default void flush() {

    }

    @Override
    default <S extends QuestaoModel> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    default <S extends QuestaoModel> List<S> saveAllAndFlush(Iterable<S> entities) {
        return List.of();
    }

    @Override
    default void deleteAllInBatch(Iterable<QuestaoModel> entities) {

    }

    @Override
    default void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    default void deleteAllInBatch() {

    }

    @Override
    default QuestaoModel getOne(Long aLong) {
        return null;
    }

    @Override
    default QuestaoModel getById(Long aLong) {
        return null;
    }

    @Override
    default QuestaoModel getReferenceById(Long aLong) {
        return null;
    }

    @Override
    default <S extends QuestaoModel> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    default <S extends QuestaoModel> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    default <S extends QuestaoModel> List<S> saveAll(Iterable<S> entities) {
        return List.of();
    }

    @Override
    default List<QuestaoModel> findAll() {
        return List.of();
    }

    @Override
    default List<QuestaoModel> findAllById(Iterable<Long> longs) {
        return List.of();
    }

    @Override
    default <S extends QuestaoModel> S save(S entity) {
        return null;
    }

    @Override
    default Optional<QuestaoModel> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    default boolean existsById(Long aLong) {
        return false;
    }

    @Override
    default long count() {
        return 0;
    }

    @Override
    default void deleteById(Long aLong) {

    }

    @Override
    default void delete(QuestaoModel entity) {

    }

    @Override
    default void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    default void deleteAll(Iterable<? extends QuestaoModel> entities) {

    }

    @Override
    default void deleteAll() {

    }

    @Override
    default List<QuestaoModel> findAll(Sort sort) {
        return List.of();
    }

    @Override
    default Page<QuestaoModel> findAll(Pageable pageable) {
        return null;
    }

    @Override
    default <S extends QuestaoModel> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    default <S extends QuestaoModel> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    default <S extends QuestaoModel> long count(Example<S> example) {
        return 0;
    }

    @Override
    default <S extends QuestaoModel> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    default <S extends QuestaoModel, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
