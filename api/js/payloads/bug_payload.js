exports.create = function(product, component, summary, version, op_sys, rep_platform){
  return {
    'product': product,
    'component': component,
    'summary': summary,
    'version': version,
    'op_sys': op_sys,
    'rep_platform': rep_platform
  }
}
