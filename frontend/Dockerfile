FROM node:alpine
WORKDIR /workspace/app/frontend

COPY src src
COPY angular.json .
COPY package.json .
COPY package-lock.json .
COPY tsconfig.json .
COPY tsconfig.app.json .
COPY tsconfig.spec.json .

RUN npm install

ENTRYPOINT ["npm","start","--","--host","0.0.0.0"]
