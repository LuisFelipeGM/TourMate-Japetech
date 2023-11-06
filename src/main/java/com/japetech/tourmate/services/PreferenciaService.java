package com.japetech.tourmate.services;

import com.japetech.tourmate.dtos.PreferenciaDto;
import com.japetech.tourmate.models.PreferenciaModel;
import com.japetech.tourmate.models.UsuarioModel;
import com.japetech.tourmate.repositories.PreferenciaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PreferenciaService extends EntityService<PreferenciaModel> {

    private static final Logger log = LoggerFactory.getLogger(PreferenciaService.class);

    private final PreferenciaRepository preferenciaRepository;


    PreferenciaService(JpaRepository<PreferenciaModel, Long> repository, PreferenciaRepository preferenciaRepository) {
        super(repository);
        this.preferenciaRepository = preferenciaRepository;
    }

    public PreferenciaModel  adicionaPreferenciaPadrao(UsuarioModel usuarioModel) {
        try {
            PreferenciaModel pref = new PreferenciaModel();
            pref.setClimaFrio("N");
            pref.setClimaQuente("S");
            pref.setViagemTurismo("S");
            pref.setViagemNegocio("N");
            pref.setUsuario(usuarioModel);

            log.info("Cadastrando Preferência Padrão");

            return repository.save(pref);
        } catch (DataIntegrityViolationException e) {
            log.error("Erro ao salvar a nova preferência: Restrição exclusiva violada.");
            throw new RuntimeException("Erro ao salvar a nova preferência: Esta preferência já existe no sistema.");
        } catch (Exception e) {
            log.error("Erro ao criar as preferências do usuário " + e.getMessage());
            throw new RuntimeException("Erro ao salvar nova preferência.");
        }
    }

    public PreferenciaModel putPreferencia(PreferenciaDto preferenciaDto, Long id){
        try {
            Optional<PreferenciaModel> preferenciaOptional = preferenciaRepository.findById(id);
            if (preferenciaOptional.isPresent()){
                PreferenciaModel pref = preferenciaOptional.get();
                BeanUtils.copyProperties(preferenciaDto, pref);

                log.info("Atualizando Preferencia de ID: " + id);
                return repository.save(pref);

            } else {
                throw new RuntimeException("Erro ao atualizar preferencia.");
            }
        } catch (Exception e) {
            log.error("Erro ao copiar as propriedades do DTO para o modelo de Preferencia: " + e.getMessage());
            throw new RuntimeException("Erro ao atualizar preferencia.");
        }
    }


}
