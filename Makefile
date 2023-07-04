APP=$(shell basename $(shell git remote get-url origin))
REGISTRY=spolish
VERSION=$(shell git describe --tags --abbrev=0)-$(shell git rev-parse --short HEAD)
TARGETOS:=linux  #linux darwin windows
TARGETARCH=amd64 # arm64 amd64 $(shell go env GOARCH)
IMAGE_TAG=${REGISTRY}/${APP}:${VERSION}-${TARGETARCH}

format:
		gofmt -s -w ./

lint:
		golint

test:
		go test -v

get:
		go get

build: format get
			CGO_ENABLED=0 GOOS=${TARGETOS} GOARCH=${TARGETARCH} go build -v -o kbot -ldflags "-X="github.com/spolish/kbot/cmd.appVersion=${VERSION}

build-linux: format get
			CGO_ENABLED=0 GOOS=linux GOARCH=$(shell go env GOARCH) go build -v -o kbot -ldflags "-X github.com/spolish/kbot/cmd.appVersion=$(VERSION)"


build-macos: format get
			CGO_ENABLED=0 GOOS=darwin GOARCH=$(shell go env GOARCH) go build -v -o kbot -ldflags "-X github.com/spolish/kbot/cmd.appVersion=$(VERSION)"

build-windows: format get
			CGO_ENABLED=0 GOOS=windows GOARCH=amd64 go build -v -o kbot.exe -ldflags "-X github.com/spolish/kbot/cmd.appVersion=$(VERSION)"

image:
		docker build  . -t ${REGISTRY}/${APP}:${VERSION}-${TARGETARCH}

push:
		docker push ${REGISTRY}/${APP}:${VERSION}-${TARGETARCH}

clean:
		rm -rf kbot kbot.exe
		docker rmi ${IMAGE_TAG}