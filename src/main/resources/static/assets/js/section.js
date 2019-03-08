Vue.component('section-list', {
    props: ['sectionId','sectionName','sectionDescription'],
    template:
        '<div class="card textc-black mt-3">\n' +
        '  <div class="card-header">\n' +
        '    <b class="d-none">{{sectionId}}</b>\n' +
        '    <h5 class="card-title">{{sectionName}}</h5>\n' +
        '  </div>\n' +
        '  <div class="card-body">\n' +
        '   <button type="button" class="close" aria-label="Close" onclick="vm.deleteSection(this)">\n' +
        '       <span aria-hidden="true">&times;</span>\n' +
        '   </button>'+
        '    <div class="card-info">'+
        '      <p class="card-text">'+ 
        '        <b>Описание секции</b>'+
        '        <br>{{sectionDescription}}</br>'+
        '      </p>'+
        '      <p class="d-none">Groups in this section:</p>\n' +
        '      <p class="card-text d-none">         \n' +
        '        <a href="/group/1" class="badge textc-white bgc-primary">Default 1</a>\n' +
        '        <a href="/group/2" class="badge textc-white bgc-primary">Default 2</a>\n' +
        '        <a href="/section/1" class="badge textc-black">see all...</a>\n' +
        '      </p>\n' +
        '    </div>\n' +
        '    <button type="button" class="btn textc-white bgc-primary" onClick="vm.openEditSection(this)"  data-toggle="modal" id data-target="#editSectionModal">Manage section</button>\n' +
        '    <a href="/section/1" class="btn textc-white bgc-primary">Enter</a>\n' +
        '  </div>\n' +
        '</div>'
});


var vm = new Vue({
    el: '#app',
    data: {
        sections: [{
            id: '1',
            name: 'Box',
            description: 'Box is cool!'
        },
        {
            id: '2',
            name: 'Football',
            description: 'Football is cool!'
        }]
    },
    mounted() {
        var url_string = window.location.href;
        var url = new URL(url_string);
        if (url.searchParams.get("id")!=''){
            axios
            .get('/api/v1/section/'+ url.searchParams.get("id"))
            .then(response => (this.persons = response.data));
        } else {
            axios
            .get('/api/v1/section/')
            .then(response => (this.persons = response.data));
        }
    },
    methods: {
        loadSection: function () {
            var url_string = window.location.href;
            var url = new URL(url_string);
            if (url.searchParams.get("id")!=''){
                axios
                .get('/api/v1/section/'+ url.searchParams.get("id"))
                .then(response => (this.persons = response.data));
            } else {
                axios
                .get('/api/v1/section/')
                .then(response => (this.persons = response.data));
            }
        },
        addSection: function () {
            axios.post('/api/v1/section/', {
                id: "1",
                name: document.getElementById("add_section_name").value,
                description: document.getElementById("add_section_description").value,
            })
                .then(function (response) {
                    console.log(response);
                    setTimeout(vm.loadData(), 1000);
                })
                .catch(function (error) {
                    console.log(error);
                });
            this.loadSection();
        },
        editSection: function () {
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
        deleteSection: function (element) {
            var button = element;
            personId = button.parentNode.getElementsByTagName("h5")[0].getElementsByTagName("b")[0].innerText;

            axios.delete('/api/v1/person/'+personId, {})
                .then(function (response) {
                    console.log(response);
                    setTimeout(vm.loadData(), 1000);
                })
                .catch(function (error) {
                    console.log(error);
                });

        },
        openEditSection: function (element) {
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