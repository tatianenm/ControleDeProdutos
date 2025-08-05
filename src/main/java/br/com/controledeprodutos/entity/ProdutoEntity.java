        package br.com.controledeprodutos.entity;

        import jakarta.persistence.*;
        import lombok.*;

        import java.io.Serial;
        import java.io.Serializable;
        import java.math.BigDecimal;

        @Getter
        @Setter
        @Entity
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        @EqualsAndHashCode
        @Table(name = "produto")
        public class ProdutoEntity implements Serializable {

            @Serial
            private static final long serialVersionUID = -2249176396660883842L;

            @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            private Long id;

            private String nomeEmpresa;

            private String origem;

            private String tipo;

            private BigDecimal preco;

            private Integer quantidade;

            private String  nomeProduto;

        }
