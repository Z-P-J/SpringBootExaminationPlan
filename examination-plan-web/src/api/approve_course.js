import request from '@/utils/request'

export function getApproveCourseDataList(major_id) {
  return request({
    url: '/approve_course/courselist/' + major_id,
    method: 'get'
  })
}

export function AddApproveCourseData(approveCourseSet, school_id) {
  return request({
    url: '/approve_course/course/addApproveCourseSet/' + school_id,
    method: 'put',
    data: approveCourseSet
  })
}

export function removeData(major_id) {
  return request({
    url: '/exam-approve_major/' + major_id,
    method: 'delete'
  })
}

