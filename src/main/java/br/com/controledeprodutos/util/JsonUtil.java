    package br.com.controledeprodutos.util;

    import br.com.controledeprodutos.dto.interfaceDTO.DataWrapper;
    import com.fasterxml.jackson.databind.ObjectMapper;
    import lombok.extern.slf4j.Slf4j;
    import org.springframework.core.io.Resource;

    import java.io.IOException;
    import java.util.List;
    import java.util.stream.Stream;

    @Slf4j
    public class JsonUtil {

        private static final ObjectMapper objectMapper = new ObjectMapper();


        public static <T, U extends DataWrapper<T>> Stream<T> readJsonFile(Resource resource, Class<U> dataWrapperClass) {
            try {
                U dataWrapper = objectMapper.readValue(resource.getInputStream(), dataWrapperClass);

                if (dataWrapper != null && dataWrapper.getData() != null) {
                    List<T> records = dataWrapper.getData();
                    log.debug("Arquivo " + resource.getFilename() + " lido com " + records.size() + " registros.");
                    return records.stream();
                } else {
                    log.debug("Arquivo " + resource.getFilename() + " lido, mas a lista de dados está vazia ou nula.");
                    return Stream.empty();
                }
            } catch (IOException e) {
                log.error("Erro ao ler o arquivo " + resource.getFilename() + ": " + e.getMessage());
                return Stream.empty();
            }
        }

    }


