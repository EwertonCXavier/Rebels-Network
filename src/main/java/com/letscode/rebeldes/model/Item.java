package com.letscode.rebeldes.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Embeddable
@Data
public class Item {

  private int weaponQty;
  private int armorQty;
  private int waterQty;
  private int foodQty;

}

/**
 * PRÓXIMOS PASSOS:
 *
 * 1. Permitir uma relação de 1 para muitos entre os itens cadastrados (ARMA, MUNICAO, AGUA e COMIDA) através
 * da criação de um uma nova entidade no banco, a qual possui a funcionalidade de armazenamento do id do usuário
 * que terá um dos itens em seu inventário.
 *
 * A estrutura desta nova tabela/entidade é conforme a seguir:
 * 1. Chave estrangeira correspondendo ao id do usuário;
 * 2. Chave estrangeira correspondendo ao id do item que será adicionado ao inventário do usuário;
 * 3. Quantidade do item a ser adicionado;
 *
 *
 * O relacionamento da tabela do usuário será diretamente ligada a essa tabela auxiliar (a que contém somente
 * as chaves estrangeiras)
 *
 *
 */
