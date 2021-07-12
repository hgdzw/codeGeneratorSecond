package ${BasePackageName}provider.controller;
import com.qc.common.utils.context.*;
import ${BasePackageName}dao.common.*;
import ${BasePackageName}dao.dto.*;
import ${BasePackageName}dao.request.*;
import ${BasePackageName}dao.response.*;
import ${BasePackageName}dao.entity.*;
import ${BasePackageName}service.biz.*;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Author ${Author}
 * Date  ${Date}
 */

@Api(value = "API - ${ClassName}Controller")
@RestController
@RequestMapping(value = "/${EntityName}")
@CrossOrigin
public class ${ClassName}Controller {

    @Autowired
    private ${ClassName}Service ${EntityName}Service;

    @ApiOperation(value = "${ClassName}数据 listPageByRequest")
    @PostMapping(value = "/listPageByRequest")
    public QcResponse<PageResult<${ClassName}ListResponse>> listPageByRequest(@RequestBody PageInfo<${ClassName}ListRequest> request) {

        PageResult<${ClassName}ListResponse> ${EntityName}s = ${EntityName}Service.listPageByRequest(request);
        return QcResponse.success(${EntityName}s);

    }

    @ApiOperation(value = "${ClassName}数据 findById")
    @GetMapping("/findById")
    public QcResponse<${ClassName}Response> findById(@ApiParam(name="id",value="id",required=true)String id) {
        ${ClassName}Response ${EntityName} = ${EntityName}Service.findDetailById(id);

        return QcResponse.success(${EntityName});
    }

    @ApiOperation(value = "${ClassName}数据 insert")
    @PostMapping(value = "/insert" )
    public QcResponse<Boolean> insert(@RequestBody@ApiParam(name = "${ClassName} 对象",value = "传入JSON数据",required = true) ${ClassName}Request request) {

        if (${EntityName}Service.create(request) > 0) {
            return QcResponse.success(Boolean.TRUE);
        } else {
            return QcResponse.error(Boolean.FALSE);
        }
    }

    @ApiOperation(value = "${ClassName}数据 update")
    @PostMapping(value = "/update")
    public QcResponse<Boolean> update(@RequestBody@ApiParam(name = "${ClassName} 对象",value = "传入JSON数据",required = true) ${ClassName}Request request) {
        if (${EntityName}Service.updateDetail(request) > 0) {
            return QcResponse.success(Boolean.TRUE);
        } else {
            return QcResponse.error(Boolean.FALSE);
        }
    }

    @ApiOperation(value = "${ClassName}数据 delete")
    @PostMapping(value = "/delete")
    public QcResponse<Boolean> delete(@ApiParam(name="id",value="id",required=true)String id) {

        return ${EntityName}Service.deleteById(id)?QcResponse.success(true):QcResponse.error("删除失败!");

    }
}