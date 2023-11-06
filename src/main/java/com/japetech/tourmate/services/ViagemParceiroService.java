package com.japetech.tourmate.services;

import com.japetech.tourmate.dtos.ViagemParceiroDto;
import com.japetech.tourmate.models.ParceiroModel;
import com.japetech.tourmate.models.ViagemModel;
import com.japetech.tourmate.models.ViagemParceiroModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ViagemParceiroService extends EntityService<ViagemParceiroModel> {

    private static final Logger log = LoggerFactory.getLogger(ViagemParceiroService.class);

    private final ParceiroService parceiroService;
    private final ViagemService viagemService;

    ViagemParceiroService(JpaRepository<ViagemParceiroModel, Long> repository, ParceiroService parceiroService, ViagemService viagemService) {
        super(repository);
        this.parceiroService = parceiroService;
        this.viagemService = viagemService;
    }

    public ViagemParceiroModel adicionaViagemParceiro(ViagemParceiroDto viagemParceiroDto){
        try {
            Optional<ParceiroModel> optionalParceiro = parceiroService.findById(viagemParceiroDto.idParceiro());
            if (optionalParceiro.isPresent()){
                ParceiroModel parceiro = optionalParceiro.get();
                Optional<ViagemModel> optionalViagem = viagemService.findById(viagemParceiroDto.idViagem());
                if (optionalViagem.isPresent()){
                    ViagemModel viagem = optionalViagem.get();
                    ViagemParceiroModel viagemParceiro = new ViagemParceiroModel();
                    viagemParceiro.setParceiro(parceiro);
                    viagemParceiro.setViagem(viagem);

                    log.info("Cadastrando nova Viagem Parceiro");
                    return repository.save(viagemParceiro);
                } else {
                    throw new Exception("Viagem não encontrada");
                }
            } else {
                throw new Exception("Parceiro não encontrado");
            }
        } catch (Exception e) {
            log.error("Erro ao adicionar nova Viagem Parceiro: " + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

}
