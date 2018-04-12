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
	# Replace image tags in examples
	find $(BUILD_PATH)/ -name '*.yaml' -type f -exec sed -i '/image: "\?strimzi\/[a-zA-Z0-9_-.]\+:[a-zA-Z0-9_-.]\+"\?/s/:[a-zA-Z0-9_-.]\+/:$(RELEASE_VERSION)/g' {} \;
	find $(BUILD_PATH)/ -name '*.yaml' -type f -exec sed -i '/name: [a-zA-Z0-9_-]*IMAGE_TAG/{n;s/value: [a-zA-Z0-9_-.]\+/value: $(RELEASE_VERSION)/}' {} \;
	find $(BUILD_PATH)/ -name '*.yaml' -type f -exec sed -i '/name: STRIMZI_DEFAULT_[a-zA-Z0-9_-]*IMAGE/{n;s/:[a-zA-Z0-9_-.]\+/:$(RELEASE_VERSION)/}' {} \;


.PHONY: all build clean docker_build docker_push docker_tag