<script setup>
import { ref, onMounted } from 'vue';
import axios from "axios";

const base_url = "http://localhost:8080/api/v1"
const photos_endpoint = "/photos"

const data = ref({
    photos: [],
    message: "",
    email: "",
})

onMounted(() => {
    axios.get(base_url + photos_endpoint)
        .then(response => {
            data.value.photos = response.data
            //console.log(response.data)
            //console.log(data.value.photos)
        })
        .catch(err => {
            console.error(err.message)
        })
})

</script>

<template>
    <div class="container mb-5">
        <div class="row g-3 justify-content-start align-items-start flex-wrap" v-if="data.photos.length > 0">
            <div class="col-12 col-md-6 col-lg-4 h-100" v-for="photo in data.photos" v-show="photo.visible == true">
                <div class="card h-100 rounded-0 border-0 shadow">
                    <img class="card-img-top rounded-0 object-fit-cover" height="300" width="200" :src="photo.url"
                        :alt="photo.title">
                    <div class="card-body p-3 h-100 d-flex flex-column justify-content-between align-items-start gap-2">
                        <h4 class="card-title mb-0 text_italic">{{ photo.title }}</h4>
                        <p class="card-text mb-0">{{ photo.description }}</p>
                        <ul class="list-unstyled d-flex align-items-center flex-wrap justify-content-start gap-2 mb-0">
                            <li class="" v-for="category in photo.categories">
                                <small class="text-italic">
                                    #{{ category.name }}
                                </small>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<style lang="scss" scoped></style>