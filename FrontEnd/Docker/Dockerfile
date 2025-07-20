# Step 1: Build Vue Project
FROM node:lts-alpine as build-stage
WORKDIR /app
COPY FrontEnd/package*.json ./
RUN npm install
COPY FrontEnd .
RUN npm run build

# Step 2: Create Nginx Server
FROM nginx:stable-alpine as production-stage
COPY FrontEnd/Docker/nginx.conf /etc/nginx/nginx.conf

COPY --from=build-stage /app/dist /usr/share/nginx/html

CMD ["nginx", "-g", "daemon off;"]