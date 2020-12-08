package ${BasePackageName}dao.${EntityPackageName};

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

/**
 * ${Remarks}
 * Author ${Author}
 * Date  ${Date}
 */
@ApiModel(description = "${Remarks}",value = "${ClassName}")
@EqualsAndHashCode(callSuper = false)
@Data
public class ${ClassName} implements Serializable {
    private static final long serialVersionUID = 1L;

    ${Properties}
}