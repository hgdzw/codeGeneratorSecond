package ${BasePackageName}dto.${EntityPackageName};

import java.io.Serializable;
import java.util.List;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
/**
 * ${Remarks}
 * Author ${Author}
 * Date  ${Date}
 */
@ApiModel(description = "${Remarks}",value = "${ClassName}")
public class ${ClassName}DTO implements Serializable {
    private static final long serialVersionUID = 1L;

    ${Properties}
}