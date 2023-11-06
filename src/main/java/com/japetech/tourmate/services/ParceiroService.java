package com.japetech.tourmate.services;

import com.japetech.tourmate.dtos.ParceiroDto;
import com.japetech.tourmate.models.ParceiroModel;
import com.japetech.tourmate.repositories.ParceiroRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ParceiroService extends EntityService<ParceiroModel> {

    private static final Logger log = LoggerFactory.getLogger(ParceiroService.class);

    private final ParceiroRepository parceiroRepository;


    ParceiroService(JpaRepository<ParceiroModel, Long> repository, ParceiroRepository parceiroRepository) {
        super(repository);
        this.parceiroRepository = parceiroRepository;
    }

    public ParceiroModel adicionaParceiro(ParceiroDto parceiroDto) {
        try {
            ParceiroModel parceiro = new ParceiroModel();
            BeanUtils.copyProperties(parceiroDto, parceiro);

            log.info("Cadastrando novo Parceiro");

            return repository.save(parceiro);
        } catch (DataIntegrityViolationException e) {
            log.error("Erro ao salvar o novo parceiro: Restrição exclusiva violada.");
            throw new RuntimeException("Erro ao salvar o novo parceiro: Este parceiro com esse CNPJ já existe no sistema. ");
        } catch (Exception e) {
            log.error("Erro ao copiar as propriedades do DTO para o modelo de Parceiro: " + e.getMessage());
            throw new RuntimeException("Erro ao salvar o novo parceiro.");
        }
    }

    public ParceiroModel putParceiro(ParceiroDto parceiroDto, Long id){
        try {
            Optional<ParceiroModel> parceiroOptional = parceiroRepository.findById(id);
            if (parceiroOptional.isPresent()){
                ParceiroModel parceiro = parceiroOptional.get();
                BeanUtils.copyProperties(parceiroDto, parceiro);

                log.info("Atualizando Parceiro de ID: " + id);
                return repository.save(parceiro);
            } else {
                throw new RuntimeException("Erro ao salvar o novo parceiro.");
            }
        } catch (Exception e) {
            log.error("Erro ao copiar as propriedades do DTO para o modelo de Parceiro: " + e.getMessage());
            throw new RuntimeException("Erro ao atualizar novo parceiro");
        }
    }


}
