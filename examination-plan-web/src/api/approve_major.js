import request from '@/utils/request'

export function list(params) {
  return request({
    url: '/approve_major',
    method: 'get',
    params
  })
}

export function addApproveMajor(param, school_id) {
  return request({
    url: '/approve_major/single/' + school_id,
    method: 'post',
    data: param
  })
}

export function addApproveMajorByBatch(param, ids) {
  return request({
    url: '/approve_major/batch/' + ids,
    method: 'post',
    data: param
  })
}

export function getApproveMajorDataList(id) {
  return request({
    url: '/approve_major/majorlist/' + id,
    method: 'get'
  })
}

export function getApproveCourseDataList(id) {
  return request({
    url: '/approve_major/courselist/' + id,
    method: 'get'
  })
}

export function getApproveNewData(approve_id) {
  return request({
    url: '/approve_major/newmajor/' + approve_id,
    method: 'get'
  })
}
