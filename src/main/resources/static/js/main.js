function getIndex(list, id) {
    for (var i = 0; i < list.length; i++) {
        if (list[i].id === id) {
            return i;
        }
    }
    return -1;
}

var productAPI = Vue.resource('/products{/id}');

Vue.component('product-form', {
    props: ['products', 'product'],
    data: function () {
        return {
            id: '',
            name: ''
        }
    },
    watch: {
        product: function (newVal, oldVal) {
            this.name = newVal.name;
            this.id = newVal.id;
        }
    },
    template:
        '<div>' +
        '<input type="text" placeholder="Add new product" v-model="name"/>' +
        '<input type="button" value="Add" @click="save"/>' +
        '</div>',
    methods: {
        save: function () {
            var product = {name: this.name};
            if (this.id) {
                productAPI.update({id: this.id}, product).then(result =>
                    result.json().then(data => {
                        var index = getIndex(this.allProducts, data.id);
                        this.product.splice(index, 1, data);
                    })
                )
            } else {
                productAPI.save({}, product).then(result =>
                    result.json().then(data => {
                        this.allProducts.push(data);
                        this.name = ''
                    })
                )
            }
        }
    }
});

Vue.component('product-row', {
    props: ['product', 'editMethod'],
    template: '<div>' +
        '<i>{{product.id}}:</i> {{product.name}}' +
        '<span>' +
        '<input type="button" value="Edit" @click="edit"/>' +
        '</span>' +
        '</div>'
    ,
    methods: {
        edit: function () {
            this.editMethod(this.name);
        }
    }
});

Vue.component('allProducts-list', {
    props: ['allProducts'],
    data: function () {
        return {
            product: null
        }
    },
    template:
        '<div>' +
        '<product-form :allProducts="allProducts" :product="product"/>' +
        '<product-row v-for="product in allProducts" :key="product.id" :product="product" :editMethod="editMethod"/>' +
        '</div>',
    created: function () {
        productAPI.get().then(result =>
            result.json().then(data =>
                data.forEach(product => this.allProducts.push(product))
            )
        )
    },
    methods: {
        editMethod: function (product) {
            this.product = product;
        }
    }
});

var app = new Vue({
    el: '#app',
    template: '<allProducts-list :allProducts="allProducts"/>',
    data: {
        allProducts: []
    }
});