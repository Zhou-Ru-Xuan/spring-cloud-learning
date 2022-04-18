import request from '@/utils/request'


export default {
    //添加产品信息
    addGood(good) {
        return request({
            url: `/good/consumer/add`,
            method: 'post',
            data: good
        })
    },
    //删除产品信息
    deleteGoodById(id) {
        return request({
            url: `/good/consumer/deleteGoodById/${id}`,
            method: 'delete'
        })
    },
    //修改产品信息
    updateGood(good) {
        return request({
            url: `/good/consumer/updateGoodById`,
            method: 'post',
            data: good
        })
    },




    //获取分页条件查询结果
    getGoodListPage(current, limit, goodQuery) {
        return request({
            url: `/good/consumer/pageGoodCondition/${current}/${limit}`,
            method: 'post',
            data: goodQuery
        })
    },


    //根据id查询产品信息
    getGood(id) {
        return request({
            url: `/good/consumer/getGood/${id}`,
            method: 'get'
        })
    },
    // getGoods() {
    //     return request({
    //         url: `/good/findAll`,
    //         method: 'get'
    //     })
    // },

    // //添加产品类型
    // addGoodType(goodType) {
    //     return request({
    //         url: `/goodType/addGoodType`,
    //         method: 'post',
    //         data: goodType
    //     })
    // }, //删除产品类型
    // deleteGoodTypeById(id) {
    //     return request({
    //         url: `/goodType/${id}`,
    //         method: 'delete'
    //     })
    // },
    // //修改产品类型
    // updateGoodType(goodType) {
    //     return request({
    //         url: `/goodType/updateGoodType`,
    //         method: 'post',
    //         data: goodType
    //     })
    // },

    //查询所有的产品类型
    getGoodTypes() {
        return request({
            url: '/good/consumer/getVenderNameList',
            method: 'get',
        })
    },

    // //分页查询产品类型
    // getGoodTypeListPage(current, limit, goodTypeQuery) {
    //     return request({
    //         url: `/goodType/pageGoodTypeCondition/${current}/${limit}`,
    //         method: 'post',
    //         data: goodTypeQuery
    //     })
    // },
    // //根据id查询产品类型
    // getGoodType(id) {
    //     return request({
    //         url: `/goodType/getGoodType/${id}`,
    //         method: 'get'
    //     })
    // },
}