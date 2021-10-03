<template>
  {{ lat }}
  {{ lon }}
</template>
<script>
import { ref } from 'vue'

const options = {
  enableHighAccuracy: true,
  timeout: 5000,
  maximumAge: 0
}

export default {
  setup() {
    const lat = ref('')
    const lon = ref('')
    const success = function (pos) {
      let crd = pos.coords;
      console.log('Your current position is:');
      lat.value = crd.latitude
      console.log('Latitude : ' + crd.latitude);
      lon.value = crd.longitude
      console.log('Longitude: ' + crd.longitude);
      console.log('More or less ' + crd.accuracy + ' meters.');
    }

    const error = function (err) {
      console.warn('ERROR(' + err.code + '): ' + err.message);
    };

    navigator.geolocation.getCurrentPosition(success, error, options);

    return { lat, lon }
  },
}
</script>