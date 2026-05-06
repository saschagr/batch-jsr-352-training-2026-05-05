package de.mathema.batch.job;

import java.util.List;

import jakarta.batch.api.chunk.AbstractItemWriter;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@RequestScoped
@Named
public class JpaHeroItemWriter extends AbstractItemWriter {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public void writeItems(List<Object> items) throws Exception {
	items.forEach(item -> entityManager.persist(item));  
    //items.forEach(entityManager::persist);
  }
}
