from flask_restful import fields, marshal_with

def CodeMapping(code,msg=None):
    cmap = {
        0:"success",
    }
    if code in cmap:
        return cmap[code]
    return msg or "unknown code message"

def format_response(obj,code=0,msg=None):
    return {"code":code,"message":CodeMapping(code,msg),"object":obj}

def failure(code, msg=None, obj=None):
    return format_response(obj,code,msg)
def success(obj, code=0, msg=None):
    return format_response(obj,code,msg)

def format_response_with(spec_fields):
    format_response_fields = {
        "code": fields.Integer,
        "message": fields.String,
        "object": fields.Nested(spec_fields),
    }
    return marshal_with(format_response_fields)

def unauthorized():
    return failure(02, 'unauthorized access')