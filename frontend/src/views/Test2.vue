<script>
import { ref } from 'vue'

const options = {
  enableHighAccuracy: true,
  timeout: 5000,
  maximumAge: 0
}

export default {
  setup() {

    const success = function (pos) {
      let crd = pos.coords;
      console.log('Your current position is:');
      console.log('Latitude : ' + crd.latitude);
      console.log('Longitude: ' + crd.longitude);
      console.log('More or less ' + crd.accuracy + ' meters.');
    }

    const error = function (err) {
      console.warn('ERROR(' + err.code + '): ' + err.message);
    };

    navigator.geolocation.getCurrentPosition(success, error, options);

    return {}
  },
}
</script>