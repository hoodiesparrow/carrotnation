FROM node:lts-alpine as build-stage
WORKDIR /frontend
COPY package*.json ./

RUN npm install
COPY . .
RUN npm run build

FROM nginx:stable-alpine as production-stage
RUN rm /etc/nginx/conf.d/default.conf
COPY ./nginx/frontend.conf /etc/nginx/conf.d/frontend.conf

COPY --from=build-stage ./frontend/dist /usr/share/nginx/html/frontend
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]

RUN apk add --no-cache tzdata
ENV TZ=Asia/Seoul
