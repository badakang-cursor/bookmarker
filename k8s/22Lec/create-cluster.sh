echo "k8s Cluster Initializing..."

kind create cluster --name bookmarker --config 0-kind-config.yaml

echo "\n-----------------------------------------------------\n"

echo "NGINX Ingress Installing ..."

kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/refs/heads/main/deploy/static/provider/kind/deploy.yaml

echo "\n-----------------------------------------------------\n"

echo "NGINX Ingress Reading...."

sleep 10

kubectl wait --namespace ingress-nginx \
  --for=condition=ready pod \
  --selector=app.kubernetes.io/component=controller \
  --timeout=90s

  echo "\n"

  echo "NGINX Final ..."