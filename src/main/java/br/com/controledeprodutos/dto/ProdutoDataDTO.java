package br.com.controledeprodutos.dto;

import br.com.controledeprodutos.dto.interfaceDTO.DataWrapper;

import java.util.List;

public class ProdutoDataDTO implements DataWrapper<ProdutoJsonDTO> {

        private List<ProdutoJsonDTO> data;

        @Override
        public List<ProdutoJsonDTO> getData() {
                return data;
        }

        public void setData(List<ProdutoJsonDTO> data) {
                this.data = data;
        }



}
