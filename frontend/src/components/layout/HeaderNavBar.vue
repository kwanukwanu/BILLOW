<template>
  <b-navbar toggleable="lg">
    <b-navbar-nav>
      <br>
      <b-nav-item>
        <router-link :to="{ name: 'main' }" class="nav-link">
          <span class="logo">
            <svg width="167" height="28" fill="none" xmlns="http://www.w3.org/2000/svg">
              <path
                d="M.686 1.052h13.862c1.693 0 3.165.182 4.416.548 1.262.354 2.308.846 3.138 1.477.83.631 1.444 1.384 1.842 2.258.41.863.615 1.798.615 2.806 0 1.649-.52 3.093-1.561 4.333 1.195.708 2.086 1.627 2.673 2.755.587 1.13.88 2.413.88 3.852 0 1.206-.205 2.297-.614 3.27a6.403 6.403 0 0 1-1.843 2.507c-.82.687-1.86 1.218-3.121 1.594-1.251.365-2.729.548-4.433.548H.686V1.052Zm15.887 9.96c.454 0 .897.017 1.328.05.742-.708 1.113-1.516 1.113-2.423 0-.941-.371-1.666-1.113-2.175-.73-.52-1.804-.78-3.22-.78H5.948v5.329h10.625Zm.1 11.406c1.417 0 2.49-.304 3.22-.913.742-.609 1.113-1.467 1.113-2.573 0-1.107-.37-1.97-1.112-2.59-.73-.62-1.804-.93-3.221-.93H5.948v7.006h10.725Zm14.56-21.366h5.295V27h-5.296V1.052Zm12.019 0h5.263v21.333h15.09V27H43.252V1.052Zm25.467 0h5.262v21.333h15.091V27H68.72V1.052Zm23.94 12.9c0-1.96.359-3.753 1.078-5.38A12.155 12.155 0 0 1 96.76 4.39c1.306-1.174 2.866-2.081 4.681-2.723 1.827-.642 3.846-.963 6.06-.963 2.202 0 4.217.321 6.043.963 1.826.642 3.387 1.55 4.682 2.723a12.064 12.064 0 0 1 3.038 4.183c.719 1.627 1.079 3.42 1.079 5.38 0 1.97-.36 3.779-1.079 5.428a12.359 12.359 0 0 1-3.038 4.233c-1.295 1.184-2.856 2.109-4.682 2.773-1.826.653-3.841.98-6.043.98-2.214 0-4.233-.327-6.06-.98-1.815-.664-3.375-1.588-4.681-2.773a12.455 12.455 0 0 1-3.022-4.233c-.72-1.65-1.079-3.459-1.079-5.429Zm5.262 0c0 1.338.238 2.545.714 3.618a8.198 8.198 0 0 0 2.009 2.756c.863.753 1.876 1.334 3.038 1.743 1.173.41 2.446.615 3.818.615 1.372 0 2.64-.205 3.802-.615 1.173-.41 2.186-.99 3.038-1.743a8.208 8.208 0 0 0 2.009-2.756c.487-1.073.73-2.28.73-3.619 0-1.339-.243-2.54-.73-3.602a7.74 7.74 0 0 0-2.009-2.69c-.852-.741-1.865-1.306-3.038-1.693-1.162-.388-2.43-.581-3.802-.581-1.372 0-2.645.193-3.818.58-1.162.388-2.175.953-3.038 1.694a7.73 7.73 0 0 0-2.01 2.69c-.475 1.062-.713 2.263-.713 3.602Zm26.911-12.9h5.744l7.039 16.75 1.627 4.052 1.279-4.051 2.589-6.11-4.665-10.641h5.412l1.91 5.063 1.776-5.063h5.412l-4.665 10.641 2.64 6.243 1.593 3.918 1.229-3.985 7.072-16.817h5.744L155.113 27H149.9l-2.307-5.146-1.876-4.998-1.876 4.997L141.5 27h-5.213L124.832 1.052Z"
                fill="#fff" />
            </svg>
          </span>
        </router-link>
      </b-nav-item>
      <b-nav-item>
        <router-link :to="{ name: 'main' }" class="nav-link">홈</router-link>
      </b-nav-item>
      <b-nav-item>
        <router-link :to="{ name: 'situation' }" class="nav-link">상황별 추천</router-link>
      </b-nav-item>
      <b-nav-item>
        <router-link :to="{ name: 'like' }" class="nav-link">내가 찜한 콘텐츠</router-link>
      </b-nav-item>
    </b-navbar-nav>
  </b-navbar>
</template>
<script>
export default {
  methods: {
    gotoPage(link) {
      this.$router.push(link);
    },
  },
};
</script>

<style scoped>
nav .navbar-nav a.router-link-active {
  color: white;
  font-weight: 400;
}

nav .navbar-nav a:hover {
  text-decoration: none;
  color: #E5E5E5;
}

.logo {
  padding-left: 42px;
  margin-left: 0;
  padding-right: 42px;
}

.navbar {
  font-size: large;
  color: #a1a1a1;
}

.nav-link {
  color: #a1a1a1;
}

.navbar-expand-lg {
  padding: 8px 0px;
  height:9vh;
}
</style>
