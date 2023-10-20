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
        <div class="row g-3" v-if="data.photos.length > 0">
            <div class="col-12 col-md-6 col-lg-4" v-for="photo in data.photos" v-show="photo.visible == true">
                <div class="rounded-0 border-0 shadow h-100">
                    <div class="position-relative">
                        <img class="card-img-top rounded-0 object-fit-cover" height="300" width="200" :src="photo.url"
                            :alt="photo.title">
                        <span class="rounded-pill text-light px-3 position-absolute shadow fw-semibold"
                            style="bottom: 1rem; left: 1rem; background-color: rgba(0,0,0, 50%) !important;">@{{
                                photo.user.username }}</span>
                    </div>
                    <div class="p-3 d-flex flex-column justify-content-start align-items-start gap-2">
                        <h4 class="mb-0 text_italic">{{ photo.title }}</h4>
                        <p class="mb-0 description">{{ photo.description }}</p>
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

<style lang="scss" scoped>
.description {
    display: -webkit-box;
    max-width: 100%;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
    overflow: hidden;
    text-overflow: ellipsis;
}
</style>