PROJECT_NAME=examples

RELEASE_VERSION ?= latest
RELEASE_PATH ?= ../strimzi-$(RELEASE_VERSION)/$(PROJECT_NAME)
BUILD_PATH ?= ../build/$(PROJECT_NAME)

release:
	mkdir -p $(RELEASE_PATH)
	cp -r ./install $(RELEASE_PATH)/
	cp -r ./templates $(RELEASE_PATH)/
	cp -r ./configmaps $(RELEASE_PATH)/


build:
	mkdir -p $(BUILD_PATH)
	# Copy examples to build path
	cp -r ./install $(BUILD_PATH)/
	cp -r ./templates $(BUILD_PATH)/
	cp -r ./configmaps $(BUILD_PATH)/
	# Replace value of *image keys in configmaps and deployments
	find $(BUILD_PATH)/ -name '*.yaml' -type f -exec sed -i '/image: "\?strimzi\/[a-zA-Z0-9_-.]\+:[a-zA-Z0-9_-.]\+"\?/{s/:[a-zA-Z0-9_-.]\+/:$(DOCKER_TAG)/;s#strimzi/#${DOCKER_ORG}/#}' {} \;
	# Replace value of (KAFKA|ZOOKEEPER)_IMAGE_TAG parameter in templates
	find $(BUILD_PATH)/ -name '*.yaml' -type f -exec sed -i '/name: [a-zA-Z0-9_-]*IMAGE_TAG/{n;s/value: [a-zA-Z0-9_-.]\+/value: $(DOCKER_TAG)/}' {} \;
    # Replace value of IMAGE_REPO_NAME parameter in templates
	find $(BUILD_PATH)/ -name '*.yaml' -type f -exec sed -i '/name: IMAGE_REPO_NAME/{n;s/value: [a-zA-Z0-9_-.]\+/value: $(DOCKER_ORG)/}' {} \;
	# Replace docker tags in STRIMZI_DEFAULT_*_IMAGE env vars for CC
	find $(BUILD_PATH)/ -name '*.yaml' -type f -exec sed -i '/name: STRIMZI_DEFAULT_[a-zA-Z0-9_-]*IMAGE/{n;s/:[a-zA-Z0-9_-.]\+/:$(DOCKER_TAG)/;s#strimzi/#${DOCKER_ORG}/#}' {} \;

docker_build: build

.PHONY: all build clean docker_build docker_push docker_tag