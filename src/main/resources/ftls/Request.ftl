package ${BasePackageName}dao.${EntityPackageName};

import java.io.Serializable;
import java.util.List;
import java.math.BigDecimal;
import java.util.Date;
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
@ApiModel(description = "${Remarks}",value = "${ClassName}Request")
@EqualsAndHashCode(callSuper = false)
@Data
public class ${ClassName}Request implements Serializable {
private static final long serialVersionUID = 1L;

    ${Properties}

}