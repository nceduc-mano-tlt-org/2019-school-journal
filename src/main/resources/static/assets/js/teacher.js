Vue.component('personlist', {
    props: ['personId','personName','personLastName'],
    template:
        '<div class="card d-inline-block mt-2" style="width: 18rem;" id="comp">\n' +
        '<div class="card-body">\n' +
        '<button type="button" class="close" aria-label="Close" onclick="vm.deletePerson(this)">\n' +
        '  <span aria-hidden="true">&times;</span>\n' +
        '</button>'+
        '<h5 class="card-title d-none" >ID: <b>{{personId}}</b></h5>\n' +
        '<h6 class="card-subtitle mb-2 text-muted" >Name: <b>{{personName}}</b></h6>\n' +
        '<h6 class="card-subtitle mb-2 text-muted" >Last name: <b>{{personLastName}}</b></h6>\n' +
        '<p class="card-text">Cars:</p>\n' +
        '<!-- Button trigger modal -->\n' +
        '<button type="button" class="btn btn-primary" onClick="vm.openEditPerson(this)"  data-toggle="modal" id data-target="#editModalCenter">\n' +
        'Manage person\n' +
        '</button>\n' +
        '</div>\n' +
        '</div>'
})


var vm = new Vue({
    el: '#app',
    data: {
        persons: []
    },
    mounted() {
        axios
            .get('/api/v1/person/')
            .then(response => (this.persons = response.data));
    },
    methods: {
        loadData: function () {
                axios
                    .get('/api/v1/person/')
                    .then(response => (this.persons = response.data));
        },
        addPerson: function () {
            axios.post('/api/v1/person/', {
                id: "1",
                name: document.getElementById("add_person_name").value,
                lastName: document.getElementById("add_person_last_name").value,
                cars: []
            })
                .then(function (response) {
                    console.log(response);
                    setTimeout(vm.loadData(), 1000);
                })
                .catch(function (error) {
                    console.log(error);
                });
            this.loadData();
        },
        editPerson: function () {
            axios.put('/api/v1/person/', {
                id: document.getElementById("edit_person_id").value,
                name: document.getElementById("edit_person_name").value,
                lastName: document.getElementById("edit_person_last_name").value,
                cars: []
            })
                .then(function (response) {
                    console.log(response);
                    setTimeout(vm.loadData(), 1000);
                })
                .catch(function (error) {
                    console.log(error);
                });

        },
        deletePerson: function (element) {
            personId = element.parentNode.getElementsByTagName("h5")[0].getElementsByTagName("b")[0].innerText;

            axios.delete('/api/v1/person/'+personId, {})
                .then(function (response) {
                    console.log(response);
                    setTimeout(vm.loadData(), 1000);
                })
                .catch(function (error) {
                    console.log(error);
                });

        },
        openEditPerson: function (element) {
            var button = element;
            personId = button.parentNode.getElementsByTagName("h5")[0].getElementsByTagName("b")[0].innerText;
            personName = button.parentNode.getElementsByTagName("h6")[0].getElementsByTagName("b")[0].innerText;
            personLastName = button.parentNode.getElementsByTagName("h6")[1].getElementsByTagName("b")[0].innerText;
            document.getElementById("edit_person_id").value = personId;
            document.getElementById("edit_person_name").value = personName;
            document.getElementById("edit_person_last_name").value = personLastName;

        }
    }
});
